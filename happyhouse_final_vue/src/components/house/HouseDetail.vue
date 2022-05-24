<template>
  <b-modal
    id="house-detail-modal"
    ref="modal"
    :title="house.apartmentName"
    class="bv-example-row"
    @hidden="CLEAR_HOUSE"
  >
    <b-container fluid>
      {{ house.apartmentName }}
      <div id="newMap" style="width: 400px; height: 300px"></div>
    </b-container>
    <template #modal-footer>
      <div class="">
        <p class="float-left"></p>
        <b-button
          variant="primary"
          size="sm"
          class="float-right"
          @click="addApt"
        >
          관심지역 추가
        </b-button>
        <b-button
          variant="danger"
          size="sm"
          class="float-right"
          @click="deleteApt"
        >
          관심지역 취소
        </b-button>
      </div>
    </template>
  </b-modal>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import { addLikeApt } from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "hospitals"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSE"]),
    ...mapActions(houseStore, ["getHouseDetail", "getHospitalList"]),
    addApt() {
      addLikeApt(
        { userid: this.userInfo.userid, aptCode: this.house.aptCode },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    deleteApt() {},

    makeMarkers() {
      this.markers = [];
      let marker = null;
      //병원 위치 넣기
      var ImgHpt = require("@/assets/hospital.png");
      this.hospitals.response.body.items.item.forEach((hospital) => {
        marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(
            parseFloat(hospital.wgs84Lat),
            parseFloat(hospital.wgs84Lon),
          ),
          title: hospital.dutyName,
          image: new kakao.maps.MarkerImage(
            ImgHpt,
            new kakao.maps.Size(24, 35),
          ),
        });
        this.markers.push(marker);

        var infowindow = new kakao.maps.InfoWindow({
          position: new kakao.maps.LatLng(hospital.wgs84Lat, hospital.wgs84Lon),
          content: `<div style="width:200px;
          display: block;
          background: #50627F;
          color: #fff;
          text-align: center;
          height: 24px;
          line-height:22px;
          border-radius:4px;
          padding:0px 10px;">${hospital.dutyName}</div>`,
        });
        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow),
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow),
        );
      });

      //아파트 위치 넣기
      var ImgApt = require("@/assets/flat.png");
      marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(
          parseFloat(this.house.lat),
          parseFloat(this.house.lng),
        ),
        title: this.house.apartmentName,
        image: new kakao.maps.MarkerImage(ImgApt, new kakao.maps.Size(24, 35)),
      });
      marker.setMap(this.map);
      this.markers.push(marker);

      var iwContent = `<div style="width:350px; text-align:center; padding:5px;"><h5>${
          this.house.apartmentName
        }</h5></div>
      <div style="width:350px; padding:5px">가격 : ${this.house.lowest},000 ~ ${
          this.house.highest
        },000원<br>
      가까운 병원 : ${this.calculation()}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(this.house.lat, this.house.lng); //인포윈도우 표시 위치입니다

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        position: iwPosition,
        content: iwContent,
      });

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(this.map, marker);
    },
    initMap() {
      const container = document.getElementById("newMap"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng), //지도의 중심좌표.
        level: 5, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options);

      this.map.setZoomable(false);
    },
    setCenter(lat, lng) {
      this.map.setCenter(new kakao.maps.LatLng(lat, lng));
    },

    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    calculation() {
      //현재 아파트 위치에서 가장 가까운 병원 저장(거리계산 알고리즘)
      var min = Number.MAX_VALUE;
      var i = -1;
      this.hospitals.response.body.items.item.forEach((hospital, index) => {
        var x = parseFloat(hospital.wgs84Lat) - parseFloat(this.house.lat);
        var y = parseFloat(hospital.wgs84Lon) - parseFloat(this.house.lng);
        var distance = Math.sqrt(Math.abs(x * x) + Math.abs(y * y));
        if (distance < min) {
          min = distance;
          i = index;
        }
      });
      if (i == -1) {
        return `가까운 병원이 없습니다.`;
      } else {
        return `${this.hospitals.response.body.items.item[i].dutyName}`;
      }
    },
  },
  mounted() {
    /* global kakao */
    //script 태그 객체생성
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      //src 속성을 추가하며 .env.local에 등록한 서비스키 활용 동적로딩을 위해 autoload추가

      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      //document의 head에 script 추가
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
    this.makeMarkers();
  },
  updated() {
    //script 태그 객체생성
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      //src 속성을 추가하며 .env.local에 등록한 서비스키 활용 동적로딩을 위해 autoload추가

      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      //document의 head에 script 추가
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
    this.makeMarkers();
  },
};
</script>

<style></style>

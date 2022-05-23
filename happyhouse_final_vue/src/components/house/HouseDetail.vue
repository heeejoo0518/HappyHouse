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
          관심지역으로 추가
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

    makeMarkers() {
      this.markers = [];

      console.log(this.markers);
      let marker = null;
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
        marker.setMap(this.map);
        this.markers.push(marker);
      });
    },
    initMap() {
      const container = document.getElementById("newMap"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng), //지도의 중심좌표.
        level: 5, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options);
      this.map.setDraggable(false);
      this.map.setZoomable(false);
    },
    setCenter(lat, lng) {
      this.map.setCenter(new kakao.maps.LatLng(lat, lng));
    },
  },
  updated() {
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
};
</script>

<style></style>

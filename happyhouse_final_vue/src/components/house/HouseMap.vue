<!-- @format -->
<template>
  <div>
    <div id="map"></div>
    <house-detail ref="detailModal" />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HouseDetail from "./HouseDetail.vue";
const houseStore = "houseStore";

export default {
  components: { HouseDetail },
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  created() {
    this.$emit("saveToggle", this.$route.name);
  },
  computed: {
    ...mapState(houseStore, ["house", "houses"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseDetail"]),
    makeMarkers() {
      var t = this;
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
      if (this.houses.length == 0) return;

      this.map.setCenter(
        new kakao.maps.LatLng(this.houses[0].lat, this.houses[0].lng),
      );

      let marker = null;
      var ImgApt = require("@/assets/flat.png");
      this.houses.forEach((house, idx) => {
        marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(
            parseFloat(house.lat),
            parseFloat(house.lng),
          ),
          title: house.apartmentName,
          image: new kakao.maps.MarkerImage(
            ImgApt,
            new kakao.maps.Size(24, 35),
          ),
          clickable: true,
        });
        marker.id = "marker" + idx;
        this.markers.push(marker);
        var infowindow = new kakao.maps.InfoWindow({
          position: new kakao.maps.LatLng(house.lat, house.lng),
          content: `<div style="width:200px;
          display: block;
          background: #50627F;
          color: #fff;
          text-align: center;
          height: 24px;
          line-height:22px;
          border-radius:4px;
          padding:0px 10px;">${house.apartmentName}</div>`,
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
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", function () {
          t.getHouseDetail(house.aptCode);
          t.$refs.detailModal.$refs.modal.show();
        });
      });
    },
    initMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 5, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options);
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
  },
  watch: {
    houses: function () {
      this.makeMarkers();
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      //script 태그 객체생성
      const script = document.createElement("script");

      //src 속성을 추가하며 .env.local에 등록한 서비스키 활용 동적로딩을 위해 autoload추가
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      /* global kakao */
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

<style scoped>
#map {
  width: 800px;
  height: 400px;
}
</style>

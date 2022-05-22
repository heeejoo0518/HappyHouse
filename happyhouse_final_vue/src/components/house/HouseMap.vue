<!-- @format -->

<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "houses"]),
  },
  methods: {
    makeMarkers() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
      if (this.houses.length == 0) return;

      this.map.setCenter(
        new kakao.maps.LatLng(this.houses[0].lat, this.houses[0].lng),
      );

      let marker = null;
      this.houses.forEach((house, idx) => {
        marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(
            parseFloat(house.lat),
            parseFloat(house.lng),
          ),
          title: house.apartmentName,
        });
        marker.id = "marker" + idx;

        var infowindow = new kakao.maps.InfoWindow({
          content: house.apartmentName,
        });

        (function (marker, infowindow) {
          kakao.maps.event.addListener(marker, "mouseover", function () {
            infowindow.open(this.map, marker);
          });
          kakao.maps.event.addListener(marker, "mouseout", function () {
            infowindow.close();
          });
        })(marker, infowindow),
          this.markers.push(marker);
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

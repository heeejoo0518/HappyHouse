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
      positions: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "houses"]),
  },

  methods: {
    initMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 5, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options);
    },

    displayMarker() {
      this.markers.forEach((marker) => {
        this.positions.push({
          title: marker.apartmentName,
          latlng: new kakao.maps.LatLng(marker.lat, marker.lng),
        });
      });
      for (var i = 0; i < this.positions.length; i++) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: this.positions[i].latlng, // 마커를 표시할 위치
          title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        });

        // 마커에 표시할 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          title: this.positions[i].title, // 인포윈도우에 표시할 내용
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
      }
    },
    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(this.map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
    setCenter(lat, lng) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 이동 시킵니다
      this.map.setCenter(moveLatLon);
    },
  },
  watch: {
    houses: function () {
      this.markers = this.houses;
      this.setCenter(this.markers[0].lat, this.markers[0].lng);
      this.displayMarker(this.markers);
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
  },
};
</script>

<style scoped>
#map {
  width: 800px;
  height: 400px;
}
</style>

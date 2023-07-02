<template>
  <v-container fluid class="d-flex fixedHeight justify-center align-center mb-1" id="map">
    <!-- <div id="map"></div> -->
  </v-container>
</template>

<script>
export default {
  name: "KakaoMap",
  props: {
    type: String,
    attractions: Array,
    plans: Array,
  },
  components: {},
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
    };
  },

  watch: {
    attractions() {
      // console.log("관광지", this.attractions);
      this.positions = [];
      this.attractions.forEach((attr) => {
        console.log(attr);
        let obj = {};
        obj.title = attr.title;
        obj.latlng = new kakao.maps.LatLng(attr.mapy, attr.mapx);
        obj.src = attr.firstimage;
        // console.log(obj.src);

        this.positions.push(obj);
      });
      this.loadMaker();
    },

    // plans() {},
  },
  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
      //   this.loadMap();
      //   console.log(this.attractions);
    }

    setTimeout(() => {
      this.callPlan();
    }, 300);
  },

  methods: {
    callPlan() {
      const p = this.$route.path.split("/")[1];
      if (p !== "trip") {
        return;
      }
      if (this.plans.length === 0) {
        console.log("plan 없는 여행");
        return;
      }
      // console.log("관광지", this.attractions);
      this.positions = [];
      this.plans.forEach((attr) => {
        let obj = {};
        obj.title = attr.attractionTitle;
        obj.latlng = new kakao.maps.LatLng(attr.latitude, attr.longitude);
        obj.src = attr.firstImg;
        // console.log(obj.src);

        this.positions.push(obj);
      });
      this.loadMaker();
    },
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
      console.log("kakao online");
    },
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);

      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position) => {
          var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
          var moveLatLon = new kakao.maps.LatLng(lat, lon);

          // 지도 중심을 이동 시킵니다
          this.map.setCenter(moveLatLon);
        });
      }
      if (navigator.geolocation) {
        var La = 127.074454848,
          Ma = 37.54785018;
        var moveLatLon = new kakao.maps.LatLng(Ma, La);

        // 지도 중심을 이동 시킵니다
        this.map.setCenter(moveLatLon);
      }
    },

    // 지정한 위치에 마커 불러오기
    loadMaker() {
      // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
      function makeOverListener(map, marker, infowindow) {
        return function () {
          infowindow.open(map, marker);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(infowindow) {
        return function () {
          infowindow.close();
        };
      }
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      this.deleteMarker();

      // 마커를 생성합니다
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          //   image: markerImage, // 마커의 이미지
        });
        this.markers.push(marker);

        const temp =
          "<div style='width:100px; overflow:hidden;'>" +
          "<img style='width:100px;'src='" +
          position.src +
          "'/>" +
          "<div>" +
          position.title +
          "</div></div>";

        var infowindow = new kakao.maps.InfoWindow({
          content: temp, // 인포윈도우에 표시할 내용
        });

        kakao.maps.event.addListener(
          marker,
          "mouseover",
          makeOverListener(this.map, marker, infowindow)
        );
        kakao.maps.event.addListener(marker, "mouseout", makeOutListener(infowindow));
      });
      // console.log("마커수 ::: " + this.markers.length);

      // 4. 지도를 이동시켜주기
      var bounds = new kakao.maps.LatLngBounds();
      var i;
      for (i = 0; i < this.positions.length; i++) {
        const lat = this.positions[i].latlng;
        if (lat["La"] == 0 && lat["Ma"] == 0) {
          // console.log("없는좌표:" + this.positions[i].title);
          continue;
        }
        bounds.extend(lat);
      }

      this.map.setBounds(bounds);
    },
    deleteMarker() {
      // console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          // console.log(item);
          item.setMap(null);
        });
      }
    },
  },
};
</script>

<style scope>
#map {
  width: 100%;
  height: 700px;
}
</style>

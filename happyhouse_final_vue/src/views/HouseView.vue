<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>
    <b-row>
      <b-col>
        <house-search-bar
          ref="sb"
          @movepg="movePage"
          @search="searchApt"
          @toggle="toggleView"
        ></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <router-view @saveToggle="currentPage" />
    </b-row>
  </b-container>
</template>
<script>
const memberStore = "memberStore";
import { mapState } from "vuex";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import { mapActions } from "vuex";

export default {
  name: "HouseView",
  data() {
    return {
      toggle: "",
      pg: 1,
      spp: 12,
      navigation: null,
    };
  },
  components: {
    HouseSearchBar,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions("houseStore", ["getHouseList"]),
    toggleView() {
      switch (this.toggle) {
        case "houseList":
          this.searchApt();
          this.toggle = "houseMap";
          break;
        case "houseMap":
          this.searchApt(this.pg, this.spp);
          this.toggle = "houseList";
          break;
      }
      this.$router.push({ name: this.toggle });
    },
    currentPage(pgName) {
      this.toggle = pgName;
    },
    searchApt(pg, spp) {
      let data = {
        sidoName: this.$refs.sb.$refs.sido.value,
        gugunName: this.$refs.sb.$refs.gugun.value,
        dongName: this.$refs.sb.$refs.dong.value,
        aptName: this.$refs.sb.$refs.aptname.value,
        pg,
        spp,
      };
      this.getHouseList(data);
    },
    movePage() {
      // this.pg = this.pg + 1;
      this.spp += 12;
      this.searchApt(this.pg, this.spp);
    },
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>

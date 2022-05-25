<!-- @format -->

<template>
  <div>
    <b-container class="text-center">
      <b-row>
        <b-col>
          <house-search-bar
            ref="sb"
            @search="searchApt"
            @toggle="toggleView"
          ></house-search-bar>
        </b-col>
      </b-row>
    </b-container>
    <b-row>
      <router-view @search="searchApt" @saveToggle="currentPage" />
    </b-row>
  </div>
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
          this.toggle = "houseMap";
          break;
        case "houseMap":
          this.toggle = "houseList";
          break;
      }
      this.$router.push({ name: this.toggle });
    },
    currentPage(pgName) {
      this.toggle = pgName;
    },
    searchApt() {
      let data = {
        sidoName: this.$refs.sb.$refs.sido.value,
        gugunName: this.$refs.sb.$refs.gugun.value,
        dongName: this.$refs.sb.$refs.dong.value,
        aptName: this.$refs.sb.$refs.aptname.value,
      };
      this.getHouseList(data);
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

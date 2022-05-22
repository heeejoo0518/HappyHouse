<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>
    <b-row>
      <b-col>
        <house-search-bar @toggle="toggleView"></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <router-view @saveToggle="currentPage" />
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
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
  methods: {
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

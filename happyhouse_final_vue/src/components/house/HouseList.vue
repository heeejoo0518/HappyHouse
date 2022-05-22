<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3"
    ><b-row>
      <b-card-group
        class="col-md-3"
        v-for="(house, index) in houses"
        :key="index"
      >
        <house-list-item
          :house="house"
          style="margin-bottom: 3rem" /></b-card-group
    ></b-row>
    <!-- <infinite-loading @infinite="InfiniteHandler" spinner=""></infinite-loading> -->
    <house-detail />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
    HouseDetail,
  },
  data() {
    return {
      limit: 0,
    };
  },
  created() {
    this.$emit("saveToggle", this.$route.name);
  },
  computed: {
    ...mapState(houseStore, ["houses", "house"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseDetail"]),
    // infiniteHandler($state) {
    //   console.log($state);
    // },
  },
};
</script>

<style></style>

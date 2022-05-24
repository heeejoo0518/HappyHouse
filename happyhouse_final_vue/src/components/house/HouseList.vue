<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3"
    ><b-row>
      <b-card-group
        class="col-md-3"
        v-for="(house, index) in houseList"
        :key="index"
      >
        <house-list-item
          :house="house"
          @openModal="openModal"
          style="margin-bottom: 3rem" /></b-card-group
    ></b-row>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      ></div>
    </infinite-loading>

    <house-detail ref="detailModal" />
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

import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
    HouseDetail,
  },
  data() {
    return {
      houseList: [],
    };
  },
  props: {
    spp: Number,
  },
  created() {
    this.$emit("saveToggle", this.$route.name);
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    openModal() {
      this.$refs.detailModal.$refs.modal.show();
    },
    infiniteHandler($state) {
      this.$emit("search", -1, this.spp);

      setTimeout(() => {
        console.log(this.houses);
        if (this.houses.length) {
          this.houseList = this.houseList.concat(this.houses);
          $state.loaded();
          if (this.houses.length < this.spp) {
            $state.complete();
          }
        } else {
          $state.complete();
        }
      }, 1000);
    },
  },
};
</script>

<style></style>

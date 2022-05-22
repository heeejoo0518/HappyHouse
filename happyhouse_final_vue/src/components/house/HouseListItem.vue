<template>
  <b-card
    no-body
    @click="selectHouse(house.aptCode)"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    img-height="150"
    :img-src="
      require('@/assets/apt/' + ((this.house.aptCode % 20) + 1) + '.jpg')
    "
    img-alt="집이미지"
    img-top
  >
    <b-card-body>
      <b-card-title>{{ house.apartmentName }}</b-card-title>
      <b-card-sub-title class="mb-2"
        >{{ house.dong }} {{ house.highest }}</b-card-sub-title
      >
    </b-card-body>
  </b-card>
</template>

<script>
import { mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["getHouseDetail"]),
    selectHouse(aptCode) {
      this.getHouseDetail(aptCode);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>

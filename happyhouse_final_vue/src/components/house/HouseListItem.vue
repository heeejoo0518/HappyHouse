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
      <b-card-sub-title class="mb-2">{{ house.dong }}</b-card-sub-title>
    </b-card-body>
  </b-card>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["hospitals"]),
  },
  props: {
    house: Object,
  },

  methods: {
    ...mapActions(houseStore, ["getHouseDetail"]),
    selectHouse(aptCode) {
      this.$emit("openModal");
      if (this.userInfo != null) {
        this.getHouseDetail({ aptCode, userid: this.userInfo.userid });
      } else {
        this.getHouseDetail({ aptCode, userid: null });
      }
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

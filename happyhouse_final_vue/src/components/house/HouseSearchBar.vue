<!-- @format -->

<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoName"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunName"
        :options="guguns"
        @change="duplicateSearch"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="dongName"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-input
        type="search"
        v-model.lazy.trim="aptName"
        placeholder="아파트 이름 입력"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <!-- <b-button variant="outline-primary" @click="searchApt">검색</b-button> -->
      <toggle-button @change="toggleView"></toggle-button>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      aptName: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "hospitals",
    ]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_HOUSE_LIST();
    this.CLEAR_HOUSE();
    this.CLEAR_HOSPITAL_LIST();
    this.getSido();
  },
  watch: {
    aptName: function () {
      if (this.gugunName) this.searchApt();
    },
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getHospitalList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_HOUSE",
      "CLEAR_HOSPITAL_LIST",
    ]),
    gugunList() {
      this.CLEAR_HOUSE_LIST();
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_HOUSE();
      this.gugunName = null;
      if (this.sidoName) this.getGugun(this.sidoName);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongName = null;
      if (this.gugunName) {
        this.getDong({ sidoName: this.sidoName, gugunName: this.gugunName });
        this.searchApt();
      }
    },
    searchApt() {
      let data = {
        sidoName: this.sidoName,
        gugunName: this.gugunName,
        dongName: this.dongName,
        aptName: this.aptName,
      };

      this.getHouseList(data);
    },
    toggleView() {
      this.$emit("toggle");
    },

    hospitalList() {
      this.CLEAR_HOSPITAL_LIST();
      let data = {
        sidoName: this.sidoName,
        gugunName: this.gugunName,
      };
      this.getHospitalList(data);
    },
    duplicateSearch() {
      this.dongList();
      this.hospitalList();
    },
  },
};
</script>

<style></style>

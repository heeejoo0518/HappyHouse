<!-- @format -->

<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col cols="8">
      <b-row v-show="!likeapt">
        <b-col class="sm-3">
          <b-form-select
            ref="sido"
            v-model="sidoName"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            ref="gugun"
            v-model="gugunName"
            :options="guguns"
            @change="duplicateSearch"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            ref="dong"
            v-model="dongName"
            :options="dongs"
            @change="searchApt"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-input
            ref="aptname"
            type="search"
            v-model.lazy.trim="aptName"
            placeholder="아파트 이름 입력"
          ></b-form-input>
        </b-col>
      </b-row>
    </b-col>

    <b-col cols="2" class="sm-4" align="left">
      <b-button
        v-if="userInfo != null"
        :pressed.sync="likeapt"
        variant="outline-warning"
        @click="likeAptsList"
        >즐겨찾기</b-button
      >
    </b-col>
    <b-col cols="2" class="sm-4" align="center">
      <b-button @click="toggleView" variant="outline-success"
        >{{ text }}
      </b-button>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import { houseList } from "@/api/house.js";

const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      aptName: null,
      likeapt: false,
      userid: null,
    };
  },
  props: {
    text: String,
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "hospitals",
    ]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_HOUSE_LIST();
    this.CLEAR_HOUSE();
    this.CLEAR_HOSPITAL_LIST();
    this.getSido();
  },
  mounted() {
    this.sidoName = "서울특별시";
    this.gugunList();
    this.gugunName = "강북구";
    this.duplicateSearch();
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
      "getLikeAptsList",
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

    likeAptsList() {
      if (!this.likeapt) {
        //false --> 리스트
        this.searchApt();
      } else {
        this.CLEAR_HOUSE_LIST();
        // this.searchApt();
        this.hospitalList();
        this.getLikeAptsList(this.userInfo.userid);
      }
    },
  },
};
</script>

<style></style>

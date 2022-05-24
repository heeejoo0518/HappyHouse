/** @format */

import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseDetail,
  hospitalList,
  getLikeApts,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: {
      apartmentName: "",
      aptCode: 0,
    },
    hospitals: [],
    navigation: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoName, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunName, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongName, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_NAVIGATION: (state, navigation) => {
      state.navigation = navigation;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
      state.house = {
        apartmentName: "",
        aptCode: 0,
      };
    },
    CLEAR_HOUSE: (state) => {
      state.house = {
        apartmentName: "",
        aptCode: 0,
      };
    },
    CLEAR_HOSPITAL_LIST: (state) => {
      state.hospitals = [];
    },
    SET_HOSPITAL_LIST: (state, hospitals) => {
      state.hospitals = hospitals;
    },

    SET_LIKE_APTS_LIST: (state, houses) => {
      state.houses = houses.aptList;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoName) => {
      const params = {
        sido: sidoName,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getDong: ({ commit }, sidogugun) => {
      dongList(
        sidogugun,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, sidogugun) => {
      houseList(
        sidogugun,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data.houses);
          commit("SET_NAVIGATION", data.navigation);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getHouseDetail: ({ commit }, params) => {
      houseDetail(
        params,
        ({ data }) => {
          commit("SET_DETAIL_HOUSE", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHospitalList: ({ commit }, sidogugun) => {
      const SERVICE_KEY = process.env.VUE_APP_HPT_API_KEY;
      const params = {
        Q0: sidogugun.sidoName,
        Q1: sidogugun.gugunName,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      hospitalList(
        params,
        ({ data }) => {
          commit("SET_HOSPITAL_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getLikeAptsList: ({ commit }, userid) => {
      getLikeApts(
        userid,
        ({ data }) => {
          commit("SET_LIKE_APTS_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default houseStore;

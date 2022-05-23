<template>
  <b-modal
    id="house-detail-modal"
    ref="modal"
    :title="house.apartmentName"
    class="bv-example-row"
    @hidden="CLEAR_HOUSE"
  >
    <b-container fluid>
      {{ house.apartmentName }}
    </b-container>
    <template #modal-footer>
      <div class="">
        <p class="float-left"></p>
        <b-button
          variant="primary"
          size="sm"
          class="float-right"
          @click="addApt"
        >
          관심지역으로 추가
        </b-button>
      </div>
    </template>
  </b-modal>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { addLikeApt } from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseDetail",
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSE"]),
    addApt() {
      addLikeApt(
        { userid: this.userInfo.userid, aptCode: this.house.aptCode },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>

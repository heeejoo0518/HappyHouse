<template>
  <b-modal
    id="delete-user-modal"
    ref="modal"
    title="회원탈퇴"
    @show="resetModal"
    @hidden="resetModal"
    @ok="handleOk"
  >
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-form-group
        label="안전한 탈퇴를 위해 비밀번호를 다시 한 번 입력해주세요."
        label-for="pwd-input"
        invalid-feedback="현재 비밀번호를 입력해주세요."
        :state="pwdState"
      >
        <b-form-input
          id="pwd-input"
          type="password"
          v-model="pwd"
          :state="pwdState"
          required
        ></b-form-input>
      </b-form-group>
    </form>
  </b-modal>
</template>

<script>
import { mapState } from "vuex";
import { deleteUser, checkUser } from "@/api/member";
const memberStore = "memberStore";
export default {
  name: "MemberDelete",
  data() {
    return { pwd: "", pwdState: null };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    checkFormValidity() {
      if (!this.$refs.form.checkValidity()) return false;

      //비밀번호 체크하고 pwdState에 저장하기
      checkUser(
        {
          userid: this.userInfo.userid,
          userpwd: this.pwd,
        },
        ({ data }) => {
          this.pwdState = data.message === "success";
        },
        (error) => {
          console.log(error);
        },
      );

      return this.pwdState;
    },
    resetModal() {
      this.pwd = "";
      this.pwdState = null;
    },
    handleOk(bvModalEvent) {
      bvModalEvent.preventDefault();
      this.handleSubmit();
    },
    handleSubmit() {
      if (!this.checkFormValidity()) {
        return;
      }
      //삭제작업하기
      deleteUser(
        this.userInfo.userid,
        ({ data }) => {
          if (data.message === "success") {
            this.$emit("deleteUser");
            this.$bvModal.hide("delete-user-modal");
          }
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

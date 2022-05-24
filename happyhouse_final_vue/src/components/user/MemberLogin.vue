<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <!-- <b-card class="text-center mt-3" style="max-width: 40rem" align="left"> -->
        <b-jumbotron bg-variant="white" header-level="4">
          <template #header>로그인</template>
          <hr class="my-4" />
          <b-form class="text-left login-form">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group class="text-center">
              <b-button
                type="button"
                variant="outline-info"
                class="m-1"
                @click="confirm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="outline-warning"
                class="m-1"
                @click="movePage"
                >회원가입</b-button
              >
            </b-form-group>
          </b-form>
          <hr class="my-4" />
          <!-- </b-card> -->
        </b-jumbotron>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signup" });
    },
  },
};
</script>

<style scoped>
.login-form {
  margin-left: 5rem;
  margin-right: 5rem;
}
</style>

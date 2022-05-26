<template>
  <b-container>
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>아이디</label>
        </b-col>
        <b-col cols="8" align-self="start" class="item text-left"
          ><b-form-input
            ref="userid"
            type="text"
            v-model.trim="user.userid"
            :state="inputIdState"
            area-describedby="input-id-feedback"
            required
          ></b-form-input>
          <b-form-text id="input-id-help" v-if="user.userid.length < 4"
            >아이디는 4글자 이상입니다.</b-form-text
          >
          <b-form-invalid-feedback id="input-id-feedback">
            중복된 아이디입니다. 다른 아이디를 입력해주세요.
          </b-form-invalid-feedback>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>비밀번호</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input
            type="password"
            :state="inputPwdState"
            area-describedby="input-pwd-feedback"
            v-model.trim="user.userpwd"
          ></b-form-input
          ><b-form-invalid-feedback id="input-pwd-feedback">
            비밀번호는 영어와 숫자를 포함한 6글자 이상이어야 합니다.
          </b-form-invalid-feedback>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>

      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>이름</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input type="text" v-model.trim="user.username"></b-form-input
        ></b-col>
        <b-col cols="1"></b-col>
      </b-row>
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>이메일</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input
            type="email"
            :state="inputEmailState"
            aria-describedby="input-email-feedback"
            v-model.trim="user.email"
          ></b-form-input
          ><b-form-invalid-feedback id="input-email-feedback">
            정확한 이메일을 입력해주세요.
          </b-form-invalid-feedback>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </form>
    <hr class="my-4" />
    <b-button
      variant="outline-primary"
      href="#"
      class="mr-1"
      @click="handleSubmit"
      >회원가입</b-button
    >
  </b-container>
</template>

<script>
import { idCheck, register } from "@/api/member";

export default {
  name: "MemberRegisterItem",
  data() {
    return {
      user: { userid: "", userpwd: "", username: "", email: "" },
      inputIdState: null,
    };
  },
  computed: {
    //비밀번호 체크
    inputPwdState() {
      if (!this.user.userpwd) return null;
      let pattern1 = /[0-9]/; //숫자
      let pattern2 = /[a-zA-Z]/; //영어
      return (
        this.user.userpwd.length >= 6 &&
        pattern1.test(this.user.userpwd) &&
        pattern2.test(this.user.userpwd)
      );
    },

    //이메일 체크
    inputEmailState() {
      if (!this.user.email) return null;
      let pattern =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      return pattern.test(this.user.email);
    },
  },
  methods: {
    checkFormValidty() {
      if (!this.$refs.form.checkValidity()) return false;
      if (this.user.userid.length < 4) {
        this.$refs.userid.focus();
        return false;
      }
      return this.inputPwdState && this.inputEmailState;
    },
    async handleSubmit() {
      if (!this.checkFormValidty()) {
        return;
      }

      await idCheck(
        this.user.userid,
        ({ data }) => {
          if (data.message != "success") {
            this.inputIdState = false;
            return;
          } else {
            this.inputIdState = true;
          }
        },
        (error) => {
          console.log(error);
        },
      );

      await register(
        this.user,
        ({ data }) => {
          if (data.message === "success") {
            alert("회원가입이 완료되었습니다.");
            this.$router.push("/user/singin");
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

<style>
.subject {
  font-weight: bold;
  text-align: right;
  margin: 0.2rem;
}
.item {
  margin: 0.2rem;
}
</style>

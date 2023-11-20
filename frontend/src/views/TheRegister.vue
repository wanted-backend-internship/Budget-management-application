<script setup lang="ts">
import { ref } from 'vue';
import { registerUser } from '../api/AuthApi.ts';
import router from '../router';

const email = ref('');
const password = ref('');
const username = ref('');

function isPasswordValid(password) {
  const lengthRegex = /^.{10,}$/;
  const uppercaseRegex = /[A-Z]/;
  const specialCharRegex = /[!@#$%^&*]/;

  return (
    lengthRegex.test(password) &&
    uppercaseRegex.test(password) &&
    specialCharRegex.test(password)
  );
}

const handleRegister = async () => {
  if (!isPasswordValid(password.value)) {
    alert('비밀번호는 대문자와 특수문자를 포함하여 10 자 이상이여야 합니다.');
    return;
  }

  const userData = {
    email: email.value,
    password: password.value,
    username: username.value,
  };

  try {
    const result = await registerUser(userData);
    if (result) {
      await router.push('/login');
    }
  } catch (error) {
    console.log(error);
  }
};
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">회원 가입</div>
        <div class="font-label">이메일을 입력해주세요.</div>
        <input v-model="email" class="input" type="text" />
        <div class="font-label">
          대문자와 특수문자를 포함, <br />
          10 자리 이상의 비밀번호를 입력해주세요.
        </div>
        <input v-model="password" class="input" type="password" />
        <div class="font-label">닉네임을 입력해주세요.</div>
        <input v-model="username" class="input" type="text" />
        <div class="content-row">
          <button
            @click.prevent="handleRegister"
            type="submit"
            class="button-blue"
          >
            회원 가입
          </button>
          <button class="button-gray">
            <RouterLink
              to="/login"
              style="text-decoration: none; color: inherit"
              >로그인
            </RouterLink>
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/common/input';
@import '../assets/styles/common/typho';
@import '../assets/styles/common/button';
@import '../assets/styles/color/_color.scss';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}

.content-row {
  @include container(row, flex-start, center, 100%, auto);
}

.button-blue {
  @include button-blue(100px, 35px, 20px 15px 0 0);
  @include pre200(14px, whitesmoke);
}

.button-gray {
  @include button-gray(100px, 35px, 20px 10px 0 0);
  @include pre200(14px, $black);
}

.form-col {
  @include container(column, flex-start, flex-start, 310px, auto);
  padding-left: 5px;
}

.input {
  @include input(280px, 32px, 18px, 0 10px, 8px);
  margin-bottom: 10px;
}

.font_title {
  @include pre300(30px, $black);
  margin: 30px 0 15px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 10px 0 7px 0;
}
</style>

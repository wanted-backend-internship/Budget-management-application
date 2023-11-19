<script setup lang="ts">
import { computed, ref } from 'vue';
import { useQuery } from 'vue-query';
import { loginUserInfo } from '../api/UserApi.ts';
import { localLogin } from '../api/AuthApi.ts';
import { useAuthStore } from '../store/AuthStore.ts';
import router from '../router';

const email = ref('');
const password = ref('');
const authStore = useAuthStore();
const isLoggedIn = computed(() => authStore.isLoggedIn);

useQuery(['loginUserInfo'], loginUserInfo, {
  enabled: computed(() => isLoggedIn.value),
});

const handleLogin = async () => {
  const userData = {
    email: email.value,
    password: password.value,
  };

  try {
    const response = await localLogin(userData);
    if (response.status == 200) {
      authStore.setLoginStatus(true);
      await router.push('/dash-board');
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
        <div class="font_title">로그인</div>
        <div class="font-label">이메일을 입력해주세요.</div>
        <input v-model="email" class="input" type="text" />
        <div class="font-label">비밀번호를 입력해주세요.</div>
        <input v-model="password" class="input" type="password" />
        <div class="content-row">
          <button
            @click.prevent="handleLogin"
            type="submit"
            class="button-blue"
          >
            로그인
          </button>
          <button class="button-gray">
            <RouterLink
              to="/register"
              style="text-decoration: none; color: inherit"
              >회원가입
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
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}

.content-row {
  @include container(row, flex-start, center, 100%, auto);
}

.form-col {
  @include container(column, flex-start, flex-start, 310px, auto);
  padding-left: 5px;
}

.button-blue {
  @include button-blue(100px, 35px, 20px 15px 0 0);
  @include pre200(14px, whitesmoke);
}

.button-gray {
  @include button-gray(100px, 35px, 20px 10px 0 0);
  @include pre200(14px, $black);
}

.input {
  @include input(280px, 32px, 18px, 0 10px, 8px);
  margin-bottom: 10px;
}

.font_title {
  @include pre300(30px, $black);
  margin: 30px 0 20px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 10px 0 7px 0;
}
</style>

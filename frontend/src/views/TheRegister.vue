<script setup lang="ts">
import { ref } from 'vue';

const text = ref('');
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">회원 가입</div>
        <div class="font-label">이메일을 입력해주세요.</div>
        <input class="input" type="text" />
        <div class="font-label">
          대문자와 특수문자를 포함, <br />
          10 자리 이상의 비밀번호를 입력해주세요.
        </div>
        <input class="input" type="password" />
        <div class="font-label">닉네임을 입력해주세요.</div>
        <input class="input" type="text" />
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/common/input';
@import '../assets/styles/common/typho';
@import '../assets/styles/color/_color.scss';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
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
  @include pre300(36px, $black);
  margin: 60px 0 20px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 10px 0 7px 0;
}
</style>

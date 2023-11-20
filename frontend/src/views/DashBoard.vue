<script setup lang="ts">
let userInfo = localStorage.getItem('userInfo');
userInfo = userInfo ? JSON.parse(userInfo) : {};
</script>

<template>
  <div class="container-col">
    <div class="content-col">
      <div class="font_head">이번달 예산</div>
      <div class="font_money">{{ userInfo.budgetTotal }} 원</div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/color/_color.scss';
@import '../assets/styles/common/typho.scss';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
}

.content-col {
  @include container(column, flex-start, flex-start, 100%, 130px);
  background-color: $blue;
}

.font_head {
  @include pre100(16px, whitesmoke);
  margin: 25px 0 0 20px;
  width: 100%;
}

.font_money {
  @include pre200(40px, whitesmoke);
  margin-left: 20px;
}
</style>

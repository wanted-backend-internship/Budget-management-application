<script setup lang="ts">
import TheHeader from './TheHeader.vue';
import TheFooter from './TheFooter.vue';
</script>

<template>
  <div class="container-col">
    <div class="content-col">
      <TheHeader />
    </div>
    <div class="content-col" style="top: 120px">
      <router-view />
    </div>
    <div class="content-row">
      <TheFooter />
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/color/_color.scss';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
}

.content-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  position: fixed;
}

.content-row {
  @include container(row, center, center, 100%, auto);
  background-color: white;
  position: fixed;
  bottom: 0;
}
</style>

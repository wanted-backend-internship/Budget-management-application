<script setup lang="ts">
import TheHeader from './TheHeader.vue';
import TheFooter from './TheFooter.vue';
</script>

<template>
  <div class="container-col">
    <div class="content-col" style="overflow-y: hidden; height: 120px">
      <TheHeader />
    </div>
    <div class="content-col" style="top: 120px; overflow-y: scroll">
      <router-view />
    </div>
    <div class="content-row" style="overflow-y: hidden">
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
  @include container(column, flex-start, flex-start, 100%, 60%);
  position: fixed;
}

.content-row {
  @include container(row, center, center, 100%, auto);
  background-color: white;
  position: fixed;
  bottom: 0;
}
</style>

<script setup lang="ts">
import TheHeader from './TheHeader.vue';
import TheFooter from './TheFooter.vue';
</script>

<template>
  <div class="container-col">
    <div class="content-col header">
      <TheHeader />
    </div>
    <div class="content-col router-view">
      <router-view />
    </div>
    <div class="content-row footer">
      <TheFooter />
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/color/_color.scss';

.content-col.router-view {
  @include container(column, flex-start, flex-start, 100%, calc(100% - 240px));
  position: fixed;
  top: 120px;
  overflow-y: scroll;
  overflow-x: hidden;
}

.content-col.header {
  @include container(column, flex-start, flex-start, 100%, 120px);
  position: fixed;
  top: 0;
  overflow: hidden;
}

.content-row.footer {
  @include container(row, center, center, 100%, 120px);
  background-color: white;
  position: fixed;
  bottom: 0;
  overflow: hidden;
}
</style>

<script setup lang="ts">
import { ref, defineEmits } from 'vue';

const searchType = ref('category');
const emits = defineEmits(['update-type']);

function submitType() {
  emits('update-type', searchType.value);
}
</script>

<template>
  <div class="container-col">
    <div class="content-col">
      <div class="form-col">
        <div class="font_title">지출 조회</div>
        <div class="font-label">지출 조회 방법을 선택해주세요.</div>
        <select
          class="input"
          v-model="searchType"
          style="width: 270px; margin-right: 10px"
        >
          <option value="general">일반 조회</option>
          <option value="category">특정 카테고리만 조회 하기</option>
          <option value="range">
            최소 최대 금액에 해당 하는 지출만 조회 하기
          </option>
        </select>
        <div type="submit" class="button-blue" @click="submitType">
          선택완료
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../../../assets/styles/common/container';
@import '../../../assets/styles/common/button';
@import '../../../assets/styles/common/input';
@import '../../../assets/styles/color/_color';
@import '../../../assets/styles/common/typho';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}

.content-row {
  @include container(row, flex-start, center, 100%, auto);
  margin-bottom: 10px;
}

.select-box {
  @include container(row, flex-start, center, 100%, auto);
  padding: 2px;
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
}

.font_title {
  @include pre300(30px, $black);
  margin: 30px 0 20px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 10px 0 10px 0;
}
</style>

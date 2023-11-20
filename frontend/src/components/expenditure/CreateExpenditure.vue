<script setup lang="ts">
import { ref } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import router from '../../router';
import { createExpenditure } from '../../api/ExpenditureApi.ts';

library.add(faCirclePlus);

const expenditureDatas = ref([
  {
    category: '',
    expenditureAmount: '',
    expenditureAt: '',
    memo: '',
    isReadable: '',
  },
]);

const addExpenditureData = () => {
  expenditureDatas.value.push({
    category: '',
    expenditureAmount: '',
    expenditureAt: '',
    memo: '',
    isReadable: '',
  });
};

const handleExpenditureSubmit = async () => {
  try {
    const expenditureData = expenditureDatas.value.map(expenditure => ({
      category: expenditure.category,
      expenditureAmount: parseInt(expenditure.expenditureAmount),
      expenditureAt: expenditure.expenditureAt,
      memo: expenditure.memo,
      isReadable: expenditure.isReadable,
    }));

    const response = await createExpenditure(expenditureData);
    if (response.status === 200) {
    }
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">지출 등록 하기</div>
        <div v-for="(expenditure, index) in expenditureDatas" :key="index">
          <div class="font-label">카테고리와 금액을 입력해주세요.</div>
          <div class="select-box">
            <select
              v-model="expenditure.category"
              class="input"
              style="width: 105px; margin-right: 10px"
            >
              <option>식비</option>
              <option>청약</option>
              <option>보험비</option>
              <option>가스비</option>
              <option>유류비</option>
              <option>교육비</option>
              <option>경조사</option>
              <option>통신비</option>
              <option>교통비</option>
              <option>자기개발</option>
              <option>주거비</option>
              <option>미용비</option>
              <option>용돈</option>
              <option>관리비</option>
              <option>적금</option>
              <option>문화생활</option>
              <option>병원비</option>
              <option>모임비</option>
            </select>
            <input
              v-model="expenditure.expenditureAmount"
              class="input"
              type="number"
              style="width: 120px; margin-right: 10px"
            />
          </div>
          <div class="font-label">언제 지출했나요?</div>
          <input
            v-model="expenditure.expenditureAt"
            class="input"
            type="datetime-local"
            style="width: 270px; margin-right: 10px"
          />
          <div class="select-box" style="padding: 0">
            <input
              v-model="expenditure.isReadable"
              class="input"
              type="checkbox"
              style="width: 20px; height: 20px; margin: 2px 5px 0 0"
            />
            <div class="font-label">통계 포함여부를 선택해주세요.</div>
          </div>
          <div class="font-label">메모</div>
          <input
            v-model="expenditure.memo"
            class="input"
            type="text"
            style="width: 270px; margin-bottom: 20px"
          />
        </div>
        <div class="content-row">
          <button @click.prevent="addExpenditureData" class="button-gray">
            <font-awesome-icon
              icon="fa-solid fa-circle-plus"
              style="margin-right: 5px"
            />
            추가하기
          </button>
          <button
            @click.prevent="handleExpenditureSubmit"
            type="submit"
            class="button-blue"
          >
            등록
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
@import '../../assets/styles/common/container';
@import '../../assets/styles/common/button';
@import '../../assets/styles/common/input';
@import '../../assets/styles/color/_color';
@import '../../assets/styles/common/typho';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}

.content-row {
  @include container(row, flex-start, center, 100%, auto);
  margin-bottom: 50px;
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

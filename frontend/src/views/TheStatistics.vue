<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { Chart, registerables } from 'chart.js';
import { dailyStatistics, monthStatistics } from '../api/StatisticsApi.ts';

Chart.register(...registerables);

const monthStatisticsData = ref([]);
const dailyStatisticsData = ref([]);
const monthChartRef = ref(null);
const dailyChartRef = ref(null);

const fetchMonthExpenditureStatistics = async () => {
  try {
    const response = await monthStatistics();
    monthStatisticsData.value = response.data;
    createMonthlyChart(
      monthChartRef.value,
      monthStatisticsData.value,
      '월별 통계',
    );
  } catch (error) {
    console.error(error);
  }
};

const fetchDailyExpenditureStatistics = async () => {
  try {
    const response = await dailyStatistics();
    if (response.data && typeof response.data === 'object') {
      dailyStatisticsData.value = response.data;
    } else {
      console.error('Unexpected data format from dailyStatistics API');
    }

    createDailyChart(
      dailyChartRef.value,
      dailyStatisticsData.value,
      '요일별 통계',
    );
  } catch (error) {
    console.error(error);
  }
};

const createMonthlyChart = (ctx, data, title) => {
  const labels = data.map(item => item.카테고리);
  const values = data.map(item => Math.round(item.통계));
  const backgroundColors = values.map(value =>
    value >= 0 ? 'rgba(255, 99, 132, 0.2)' : 'rgba(54, 162, 235, 0.2)',
  );
  const borderColors = values.map(value =>
    value >= 0 ? 'rgba(255, 99, 132, 1)' : 'rgba(54, 162, 235, 1)',
  );

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [
        {
          label: title,
          data: values,
          backgroundColor: backgroundColors,
          borderColor: borderColors,
          borderWidth: 1,
        },
      ],
    },
    options: {
      scales: {
        x: {
          beginAtZero: true,
          position: 'center',
        },
      },
    },
  });
};

const createDailyChart = (ctx, data, title) => {
  const labels = ['오늘 총합', '저번주 총합'];
  const values = [data['오늘 총합'], data['저번주 총합']];

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [
        {
          label: title,
          data: values,
          backgroundColor: [
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 99, 132, 0.2)',
          ],
          borderColor: ['rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)'],
          borderWidth: 1,
        },
      ],
    },
    options: {
      scales: {
        x: {
          beginAtZero: true,
        },
      },
    },
  });
};

onMounted(fetchMonthExpenditureStatistics);
onMounted(fetchDailyExpenditureStatistics);
</script>

<template>
  <div class="container-col">
    <div class="content-col">
      <div class="form-col">
        <div class="font_title">이번 달은 얼마나 썼을까?</div>
        <div class="font-label">
          저번 달에 비해 얼마나 소비 했는지 보여드릴게요.
        </div>
        <canvas ref="monthChartRef"></canvas>
        <div class="font-label" style="margin-top: 20px">
          ✅ 이번 달에 존재 하는 카테고리를 기반으로 계산한 통계입니다. <br />
          ✅ 저번 달에 비해 더 많이 소비한 경우 양의 퍼센트로 계산 되며, 반대의
          경우 음의 퍼센트로 계산됩니다.
        </div>
      </div>
      <div class="form-col">
        <div class="font_title">지난 주와 비교 하기</div>
        <div class="font-label">지난 주에 비해 얼마나 더 사용 했을까?</div>
        <canvas ref="dailyChartRef"></canvas>
        <div v-if="dailyStatisticsData && '통계' in dailyStatisticsData">
          <div
            v-if="dailyStatisticsData.통계 > 0"
            class="font-label"
            style="margin-top: 20px; font-size: 20px"
          >
            <span>💸 지출량 </span>
            <span class="font-result">
              {{ Math.round(dailyStatisticsData.통계) }}</span
            >
            <span>% 증가</span>
          </div>
          <div
            v-else
            class="font-label"
            style="margin-top: 20px; font-size: 20px"
          >
            <span>👍 지출량 </span>
            <span class="font-result" style="color: #255cc7">
              {{ Math.round(dailyStatisticsData.통계) }}</span
            >
            <span>% 감소</span>
          </div>
        </div>
        <div class="font-label" style="margin: 10px 0 50px 0">
          ✅ 지난 주 해당 요일과 이번 주 해당 요일에 사용한 총 금액을 기반 으로
          계산한 결과 입니다. <br />
          ✅ 지난 주 해당 요일에 비해 더 많이 소비한 경우 양의 퍼센트로
          계산되며, 반대의 경우 음의 퍼센트로 계산됩니다.
        </div>
      </div>
    </div>
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

.form-col {
  @include container(column, flex-start, flex-start, 310px, auto);
  padding-left: 5px;
}

.font_title {
  @include pre300(30px, $black);
  margin: 40px 0 5px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 5px 0 7px 0;
}

.font-result {
  @include pre300(20px, $pink);
}
</style>

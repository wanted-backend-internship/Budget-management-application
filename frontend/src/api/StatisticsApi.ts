import axiosInstance from './interceptor/RequestInterceptor.ts';

export const monthStatistics = async () => {
  const response = await axiosInstance.post('/expenditures/statistics/month');
  console.log(response.data);
  return response;
};

export const dailyStatistics = async () => {
  const response = await axiosInstance.post('/expenditures/statistics/daily');
  console.log(response.data);
  return response;
};

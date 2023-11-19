import axiosInstance from './interceptor/RequestInterceptor.ts';
export const loginUserInfo = async () => {
  const response = await axiosInstance.post('/users/me');
  console.log(response.data);
  return response.data;
};

import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export default function useRegisterProduct() {
  const registerProduct = async ({ data, image }) => {
    const form = new FormData();
    Object.entries(data).map(([k, v]) => form.append(k, v));
    form.append('image', image);

    await axios.post(`${BASE_URL}/products`, form);
  };

  return {
    registerProduct,
  };
}

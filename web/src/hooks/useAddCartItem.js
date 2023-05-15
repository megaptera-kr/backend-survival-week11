import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export default function useAddCartItem() {
  const addCartItem = async ({ productId }) => {
    await axios.post(`${BASE_URL}/cart-line-items`, { productId, quantity: 1 });
  };

  const changeQuantity = async ({ id, quantity }) => {
    await axios.patch(`${BASE_URL}/cart-line-items/${id}`, { quantity });
  };

  return {
    addCartItem,
    changeQuantity,
  };
}

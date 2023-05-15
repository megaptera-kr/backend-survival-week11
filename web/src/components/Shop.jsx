import useFetchProducts from '../hooks/useFetchProducts';
import useFetchCart from '../hooks/useFetchCart';
import useAddCartItem from '../hooks/useAddCartItem';

import Products from './Products';
import Cart from './Cart';

export default function Shop() {
  const { products } = useFetchProducts();

  const { lineItems, reload } = useFetchCart();

  const { addCartItem, changeQuantity } = useAddCartItem();

  const handleClickProduct = async (productId) => {
    await addCartItem({ productId });

    reload();
  };

  const handleChangeQuantity = async (id, quantity) => {
    await changeQuantity({ id, quantity });

    reload();
  };

  return (
    <>
      <h1>메가테라 쇼핑몰</h1>
      <Products products={products} onClick={handleClickProduct} />
      <Cart items={lineItems} onChangeQuantity={handleChangeQuantity} />
    </>
  );
}

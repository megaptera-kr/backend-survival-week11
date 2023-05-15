import { useState } from 'react';

import Admin from './components/Admin';
import Shop from './components/Shop';

export default function App() {
  const [mode, setMode] = useState('shop');

  const handleChangeMode = (selectedMode) => {
    setMode(selectedMode);
  };

  return (
    <div>
      <button type="button" onClick={() => handleChangeMode('shop')}>
        쇼핑몰
      </button>
      <button type="button" onClick={() => handleChangeMode('admin')}>
        관리자
      </button>
      {mode === 'shop' ? <Shop /> : <Admin />}
    </div>
  );
}

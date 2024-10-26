import { Routes, Route } from 'react-router-dom';

import { Home } from '../pages/Home';
import { RegisterPage } from '../pages/Register';
import { DefaultLayout } from '../layouts/DefaultLayout';
import {RegisterProfessional} from '../pages/Forms';

export function Router() {
  return (
    <Routes>
      <Route path="/" element={<DefaultLayout />}>
        <Route path='/' element={<Home />} />
        <Route path='/register' element={<RegisterPage />} />
        <Route path='/professional' element={<RegisterProfessional/>} />
      </Route>
    </Routes>
  )
}
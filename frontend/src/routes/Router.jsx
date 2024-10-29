import { Routes, Route } from 'react-router-dom';

import { Home } from '../pages/Home';
import { RegisterPage } from '../pages/Register';
import { DefaultLayout } from '../layouts/DefaultLayout';
import { RegisterProfessional } from '../pages/Forms/RegisterProfessional';
import { RegisterPacient } from '../pages/Forms/RegisterPatient';
import { LoginPage } from '../pages/Login';
import { SearchProfessional } from '../pages/Search';

export function Router() {
  return (
    <Routes>
      <Route path="/" element={<DefaultLayout />}>
        <Route path='/' element={<Home />} />
        <Route path='/register' element={<RegisterPage />} />
        <Route path='/professional' element={<RegisterProfessional />} />
        <Route path='/patient' element={<RegisterPacient />} />
        <Route path='/login' element={<LoginPage />} />
        <Route path='/explorer' element={<SearchProfessional />} />
      </Route>
    </Routes>
  )
}
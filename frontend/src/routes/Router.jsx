import { Routes, Route } from 'react-router-dom';
import { DefaultLayout } from '../layouts/DefaultLayout';

import { Home } from '../pages/Home';
import { SearchProfessional } from '../pages/Search';
import { RegisterPage } from '../pages/Register';
import { RegisterProfessional } from '../pages/Forms/RegisterProfessional';
import { RegisterPacient } from '../pages/Forms/RegisterPatient';
import { LoginPage } from '../pages/Login';
import { ProfilePage } from '../pages/Profile';
import { ListPacient } from '../pages/ListPacient';
import { ScheduleProfessionalPage } from '../pages/Schedule/index';
import { SchedulePatientPage } from '../pages/Schedule/indexPatient';


export function Router() {
  return (
    <Routes>
      <Route path="/" element={<DefaultLayout />}>
        <Route path='/' element={<Home />} />
        <Route path='/explorer' element={<SearchProfessional />} />
        <Route path='/register' element={<RegisterPage />} />
        <Route path='/register/professional' element={<RegisterProfessional />} />
        <Route path='/register/patient' element={<RegisterPacient />} />
        <Route path='/login' element={<LoginPage />} />
        <Route path='/perfil' element={<ProfilePage />} />
        <Route path='/listPacients' element={<ListPacient />} />
        <Route path='/schedule/professional' element={<ScheduleProfessionalPage />} />
        <Route path='/schedule/patient' element={<SchedulePatientPage />} />
        
      </Route>
    </Routes>
  )
}
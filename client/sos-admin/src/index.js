import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Tutors from './tutor/Tutors';
import Students from './student/Students';
import TutorsAuthentication from './tutorAuthentication/TutorsAuthentication';
import StudentsAuthentication from './studentAuthentication/StudentsAuthentication';
import Classes from './class/Classes';
import RequestFromStudents from './requestFromStudent/RequestsFromStudent';
import Periods from './period/Periods';
import TutorAdd from './tutor/TutorAdd';
import StudentAdd from './student/StudentAdd';
import TutorAuthenticationAdd from './tutorAuthentication/TutorAuthenticationAdd';
import StudentAuthenticationAdd from './studentAuthentication/StudentAuthenticationAdd';
import ClassAdd from './class/ClassAdd';
import RequestFromStudentAdd from './requestFromStudent/RequestFromStudentAdd';
import PeriodAdd from './period/PeriodAdd';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="/tutors" element={<Tutors />} />
      <Route path="/students" element={<Students />} />
      <Route path="/tutors-authentication" element={<TutorsAuthentication />} />
      <Route path="/students-authentication" element={<StudentsAuthentication />} />
      <Route path="/classes" element={<Classes />} />
      <Route path="/requests" element={<RequestFromStudents />} />
      <Route path="/periods" element={<Periods />} />

      <Route path="/tutor-add" element={<TutorAdd />} />
      <Route path="/student-add" element={<StudentAdd />} />
      <Route path="/tutor-authentication-add" element={<TutorAuthenticationAdd />} />
      <Route path="/student-authentication-add" element={<StudentAuthenticationAdd />} />
      <Route path="/class-add" element={<ClassAdd />} />
      <Route path="/request-from-student-add" element={<RequestFromStudentAdd />} />
      <Route path="/period-add" element={<PeriodAdd />} />

    </Routes>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

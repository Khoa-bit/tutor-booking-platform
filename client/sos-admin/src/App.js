
//import './App.css';

//import axios from "axios";
import { Link } from "react-router-dom";

/*function callAPI() {
  axios.get("http://localhost:8080/api/tutors");
}*/

/*function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <button onClick={callAPI}>
          Test database
        </button>
      </header>
    </div>
  );
}*/

function App() {
  return (
    <div className="container">
      <div className="card mt-5">
        <div className="card">
          <div className="card-header d-flex justify-content-center align-items-center py-3">
            <h4>Database Administrator</h4>
          </div>
          <div className="card-body">
            <div className="row" style={{ margin: "30px 120px" }}>
              <div className="col-3 mb-5">
                <div className="d-flex justify-content-center">
                  <Link to="/tutors" className="btn btn-success">
                    Tutors
                  </Link>
                </div>
              </div>
              <div className="col-3 mb-5">
                <div className="d-flex justify-content-center">
                  <Link to="/students" className="btn btn-success">
                    Students
                  </Link>
                </div>
              </div>
              <div className="col-3 mb-5">
                <div className="d-flex justify-content-center">
                  <Link to="/tutors-authentication" className="btn btn-danger">
                    Tutors Authentication
                  </Link>
                </div>
              </div>
              <div className="col-3 mb-5">
                <div className="d-flex justify-content-center">
                  <Link
                    to="/students-authentication"
                    className="btn btn-danger"
                  >
                    Students Authentication
                  </Link>
                </div>
              </div>
              <div className="col-3">
                <div className="d-flex justify-content-center">
                  <Link to="/classes" className="btn btn-primary">
                    Classes
                  </Link>
                </div>
              </div>
              <div className="col-3">
                <div className="d-flex justify-content-center">
                  <Link to="/periods" className="btn btn-primary">
                    Periods
                  </Link>
                </div>
              </div>
              <div className="col-3">
                <div className="d-flex justify-content-center">
                  <Link to="/requests" className="btn btn-primary">
                    Requests
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;

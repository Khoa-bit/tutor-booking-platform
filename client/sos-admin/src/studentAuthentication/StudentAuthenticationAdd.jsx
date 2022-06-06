import React from "react";
import axios from "axios";
import { Link } from "react-router-dom";

class StudentAuthenticationAdd extends React.Component {
  state = {
    student_id: "",
    username: "",
    password: "",
  };

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
    console.log(this.state);
  };

  handleChange_username = (event) => {
    this.setState({ username: event.target.value });
  };

  handleChange_password = (event) => {
    this.setState({ password: event.target.value });
  };

  componentDidMount() {}

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("https://tutor-booking-system.herokuapp.com/api/add/student-authentication", {
        student_id: this.state.student_id,
        username: this.state.username,
        password: this.state.password,
      })
      .then((res) => {
        /*const tutors = res.data;
            this.setState({ tutors });*/
            window.location = "/students-authentication";
      });
  };

  render() {
    return (
      <div className="container mt-5 mb-5">
        <header>
          <h1 className="text-center">Tutor Authentication</h1>

          <div className="d-flex justify-content-center">
            <div className="card mt-5 w-50">
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Student ID</label>
                    <input
                      type="text"
                      onChange={this.handleChange_student_id}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input
                      type="text"
                      onChange={this.handleChange_username}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input
                      type="text"
                      onChange={this.handleChange_password}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="d-flex justify-content-center">
                    <button type="submit" className="btn btn-success">
                      Create
                    </button>
                    <Link to="/students-authentication" className="btn btn-outline-primary ms-5">
                      Cancel
                    </Link>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </header>
      </div>
    );
  }
}

export default StudentAuthenticationAdd;

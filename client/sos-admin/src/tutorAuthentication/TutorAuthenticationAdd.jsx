import React from "react";
import axios from "axios";
import { Link } from "react-router-dom";

class TutorAuthenticationAdd extends React.Component {
  state = {
    tutor_id: "",
    username: "",
    password: "",
  };

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
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
      .post("http://localhost:8080/api/add/tutor-authentication", {
        tutor_id: this.state.tutor_id,
        username: this.state.username,
        password: this.state.password,
      })
      .then((res) => {
        /*const tutors = res.data;
            this.setState({ tutors });*/
            window.location = "/tutors-authentication";
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
                    <label className="form-label">Tutor ID</label>
                    <input
                      type="text"
                      onChange={this.handleChange_tutor_id}
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
                    <Link to="/tutors-authentication" className="btn btn-outline-primary ms-5">
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

export default TutorAuthenticationAdd;

import React from "react";
import TutorAuthenticationRow from "./TutorAuthenticationRow";
import axios from "axios";

class TutorAuthenticationTable extends React.Component {
  state = {
    tutorsAuthentication: [],
    tutor_id: "",
    username: "",
  };

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/filter-for-tutors-authentication", {
        tutor_id: this.state.tutor_id,
        username: this.state.username,
      })
      .then((res) => {
        const tutorsAuthentication = res.data;
        this.setState({ tutorsAuthentication });
      });
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/tutors-authentication")
      .then((res) => {
        const tutorsAuthentication = res.data;
        this.setState({ tutorsAuthentication });
      })
      .catch((error) => console.log(error));
  }

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
  };

  handleChange_username = (event) => {
    this.setState({ username: event.target.value });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="row">
            <div className="col-3">
              <input
                className="form-control"
                id="tutor-id"
                name="tutor_id"
                onChange={this.handleChange_tutor_id}
                placeholder="Enter Tutor ID"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="tutor-username"
                name="tutor_username"
                onChange={this.handleChange_username}
                placeholder="Enter Tutor Username"
                type="text"
              />
            </div>

            <div className="col-1 d-flex justify-content-end">
              <button
                className="btn btn-primary"
                style={{ width: "100%" }}
                type="submit"
              >
                Search
              </button>
            </div>
          </div>
        </form>
        <div className="table-responsive border border-dark mt-3">
          <table className="table table-striped">
            <thead className="sticky-top">
              <tr className="bg-success">
                <th className="text-light">Tutor ID</th>
                <th className="text-light">Username</th>
                <th className="text-light">Password</th>
                <th className="text-light"></th>
                <th className="text-light"></th>
              </tr>
            </thead>
            <tbody>
              {/*{this.state.tutorsAuthentication.map((tutor) => (
            <TutorRow tutor={tutor} />
          ))} */}

              {Object.keys(this.state.tutorsAuthentication).map((key) => (
                <TutorAuthenticationRow
                  key={key}
                  tutorAuthentication={this.state.tutorsAuthentication[key]}
                />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default TutorAuthenticationTable;

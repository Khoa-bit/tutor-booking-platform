import React from "react";
import StudentAuthenticationRow from "./StudentAuthenticationRow";
import axios from "axios";

class StudentAuthenticationTable extends React.Component {
  state = {
    studentsAuthentication: [],
    student_id: "",
    username: "",
  };

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/filter-for-students-authentication", {
        student_id: this.state.student_id,
        username: this.state.username,
      })
      .then((res) => {
        const studentsAuthentication = res.data;
        this.setState({ studentsAuthentication });
      });
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/students-authentication")
      .then((res) => {
        const studentsAuthentication = res.data;
        this.setState({ studentsAuthentication });
      })
      .catch((error) => console.log(error));
  }

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
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
                id="student-id"
                name="student_id"
                onChange={this.handleChange_student_id}
                placeholder="Enter Student ID"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="student-username"
                name="student_username"
                onChange={this.handleChange_username}
                placeholder="Enter Student Username"
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
                <th className="text-light">Student ID</th>
                <th className="text-light">Username</th>
                <th className="text-light">Password</th>
                <th className="text-light"></th>
              </tr>
            </thead>
            <tbody>
              {/*{this.state.StudentsAuthentication.map((Student) => (
            <StudentRow Student={Student} />
          ))} */}

              {Object.keys(this.state.studentsAuthentication).map((key) => (
                <StudentAuthenticationRow
                  key={key}
                  studentAuthentication={this.state.studentsAuthentication[key]}
                />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default StudentAuthenticationTable;

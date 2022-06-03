import React from "react";
import RequestFromStudentRow from "./RequestFromStudentRow";
import axios from "axios";

class RequestFromStudentTable extends React.Component {
  state = {
    requestFromStudents: [],
    request_id: "",
    tutor_id: "",
    student_id: "",
  };

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/filter-for-requests-from-students", {
        request_id: this.state.request_id,
        tutor_id: this.state.tutor_id,
        student_id: this.state.student_id,
      })
      .then((res) => {
        const requestFromStudents = res.data;
        this.setState({ requestFromStudents });
      });
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/requests-from-students")
      .then((res) => {
        const requestFromStudents = res.data;
        this.setState({ requestFromStudents });
      })
      .catch((error) => console.log(error));
  }

  handleChange_request_id = (event) => {
    this.setState({ request_id: event.target.value });
  };

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
  };

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="row">
            <div className="col-3">
              <input
                className="form-control"
                id="request-id"
                name="request_id"
                onChange={this.handleChange_request_id}
                placeholder="Enter Request ID"
                type="text"
              />
            </div>
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
                id="student-id"
                name="student_id"
                onChange={this.handleChange_student_id}
                placeholder="Enter Student ID"
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
                <th className="text-light">Request ID</th>
                <th className="text-light">Tutor ID</th>
                <th className="text-light">Student ID</th>
                <th className="text-light"></th>
                <th className="text-light"></th>
              </tr>
            </thead>
            <tbody>
              {Object.keys(this.state.requestFromStudents).map((key) => (
                <RequestFromStudentRow
                  key={key}
                  requestFromStudent={this.state.requestFromStudents[key]}
                />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default RequestFromStudentTable;

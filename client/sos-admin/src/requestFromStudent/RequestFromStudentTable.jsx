import React from "react";
import RequestFromStudentRow from "./RequestFromStudentRow";
import axios from "axios";

class RequestFromStudentTable extends React.Component {
  state = {
    requestFromStudents: []
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

  render() {
    return (
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
            <RequestFromStudentRow key={key} requestFromStudent={this.state.requestFromStudents[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default RequestFromStudentTable;

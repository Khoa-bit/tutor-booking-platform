import React from "react";
import ClassRow from "./ClassRow";
import axios from "axios";

class ClassTable extends React.Component {
  state = {
    classes: [],
    class_id: "",
    tutor_id: "",
    student_id: "",
  };

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/filter-for-classes", {
        class_id: this.state.class_id,
        tutor_id: this.state.tutor_id,
        student_id: this.state.student_id,
      })
      .then((res) => {
        const classes = res.data;
        this.setState({ classes });
      });
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/classes")
      .then((res) => {
        const classes = res.data;
        this.setState({ classes });
      })
      .catch((error) => console.log(error));
  }

  handleChange_class_id = (event) => {
    this.setState({ class_id: event.target.value });
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
                id="class-id"
                name="class_id"
                onChange={this.handleChange_class_id}
                placeholder="Enter Class ID"
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
                <th className="text-light">Class ID</th>
                <th className="text-light">Tutor ID</th>
                <th className="text-light">Student ID</th>
                <th className="text-light">Grade</th>
                <th className="text-light">Subject</th>
                <th className="text-light">Address</th>
                <th className="text-light">Salary</th>
                <th className="text-light">Requirements</th>
                <th className="text-light"></th>
              </tr>
            </thead>
            <tbody>
              {/*{this.state.Classs.map((Class) => (
            <ClassRow Class={Class} />
          ))} */}

              {Object.keys(this.state.classes).map((key) => (
                <ClassRow key={key} class1={this.state.classes[key]} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ClassTable;

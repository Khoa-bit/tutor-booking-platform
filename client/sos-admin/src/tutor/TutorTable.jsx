import React from "react";
import TutorRow from "./TutorRow";
import axios from "axios";

class TutorTable extends React.Component {
  state = {
    tutors: [],
    tutor_id: "",
    fullname: "",
    province_city: "",
    ward_district: "",
  };

  renderTable(tutors) {
    this.setState({ tutors });
  }

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/filter-for-tutors", {
        tutor_id: this.state.tutor_id,
        fullname: this.state.fullname,
        province_city: this.state.province_city,
        ward_district: this.state.ward_district,
      })
      .then((res) => {
        const tutors = res.data;
        this.setState({ tutors });
      });
  };

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
  };

  handleChange_fullname = (event) => {
    this.setState({ fullname: event.target.value });
  };

  handleChange_province_city = (event) => {
    this.setState({ province_city: event.target.value });
  };

  handleChange_ward_district = (event) => {
    this.setState({ ward_district: event.target.value });
  };

  fetchResults() {
    axios
      .get("http://localhost:8080/api/tutors")
      .then((res) => {
        const tutors = res.data;
        this.renderTable(tutors);
        //this.setState({ tutors });
      })
      .catch((error) => console.log(error));
  }

  componentDidMount() {
    this.fetchResults();
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="row">
            <div className="col-2">
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
                id="tutor-fullname"
                name="fullname"
                onChange={this.handleChange_fullname}
                placeholder="Enter Tutor Fullname"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="province-city"
                list="province"
                name="province_city"
                onChange={this.handleChange_province_city}
                placeholder="Enter Province/City"
              />
              <datalist id="province">
                <option value="123" />
              </datalist>
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="ward-district"
                list="ward"
                name="ward_district"
                onChange={this.handleChange_ward_district}
                placeholder="Enter Ward/District"
              />
              <datalist id="ward">
                <option value="123" />
              </datalist>
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
                <th className="text-light">Fullname</th>
                <th className="text-light">Date of birth</th>
                <th className="text-light">Address</th>
                <th className="text-light"></th>
                <th className="text-light"></th>
              </tr>
            </thead>
            <tbody>
              {/*{this.state.tutors.map((tutor) => (
            <TutorRow tutor={tutor} />
          ))} */}

              {Object.keys(this.state.tutors).map((key) => (
                <TutorRow key={key} tutor={this.state.tutors[key]} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default TutorTable;

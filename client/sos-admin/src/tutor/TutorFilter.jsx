import React from "react";

class TutorFilter extends React.Component {
  state = {
    provinces_cities: [],
    wards_districts: [],
  };

  componentDidMount() {}

  render() {
    return (
      <form action="">
        <div className="row">
          <div className="col-2">
            <input
              className="form-control"
              id="tutor-id"
              name="tutor_id"
              placeholder="Enter Tutor ID"
              type="text"
            />
          </div>
          <div className="col-3">
            <input
              className="form-control"
              id="tutor-fullname"
              name="tutor_fullname"
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
    );
  }
}

export default TutorFilter;

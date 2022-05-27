import SearchIcon from "./Icons/SearchIcon";

export default function SearchBar() {
  return (
    <div className="relative mr-2 w-1/6">
      <input
        className="w-full rounded-lg border-0 bg-gray-100 pl-9 focus:ring-2 focus:ring-blue-200 focus:ring-offset-2"
        type="text"
        name="Search"
        id="Search"
        placeholder="Search for class, tutor"
      />
      <SearchIcon className="absolute top-1/2 left-2 w-5 -translate-y-1/2"></SearchIcon>
    </div>
  );
}

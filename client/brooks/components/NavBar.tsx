import { Menu, Transition } from "@headlessui/react";
import { ChevronDownIcon } from "@heroicons/react/solid";
import { AuthUser, Fullname } from "@lib/model/AuthUser";
import authService from "@lib/service/authService";
import Link from "next/link";
import { useRouter } from "next/router";
import { Fragment } from "react";
import HomeTutorIcon from "./Icons/HomeTutorIcon";
import SearchBar from "./SearchBar";

interface NavBarProps {
  user: AuthUser | null;
}

export default function NavBar({ user }: NavBarProps) {
  const router = useRouter();

  const isActive = (curPath: string) => {
    return router.pathname.startsWith(curPath);
  };

  return (
    <nav className="flex items-center justify-center gap-2 px-9 py-2">
      <HomeTutorIcon className="w-36"></HomeTutorIcon>
      <Link href="/">
        <a
          className={
            "flex items-center justify-center whitespace-nowrap px-4 py-3 text-sm hover:underline " +
            (isActive("/") &&
              "font-bold underline decoration-blue-700 decoration-4 underline-offset-8")
          }
        >
          Home
        </a>
      </Link>
      <ForParentDropdown isActive={isActive}></ForParentDropdown>
      <ForTutorDropdown isActive={isActive}></ForTutorDropdown>
      <Link href="#">
        <a
          className={
            "flex items-center justify-center whitespace-nowrap px-4 py-3 text-sm hover:underline " +
            (isActive("#")
              ? "font-bold underline decoration-blue-700 decoration-4 underline-offset-8"
              : "")
          }
        >
          News
        </a>
      </Link>
      <div className="ml-auto"></div>
      <SearchBar></SearchBar>
      {user ? (
        <div>
          <UserDropdown fullname={user.userMetadata.fullname}></UserDropdown>
        </div>
      ) : (
        <>
          <Link href="/login">
            <a
              className="flex items-center justify-center whitespace-nowrap rounded-md
        bg-blue-700 px-4 py-3 text-sm font-bold text-white transition-colors hover:bg-blue-600"
            >
              Find a tutor
            </a>
          </Link>
          <Link href="/login">
            <a
              className="flex items-center justify-center whitespace-nowrap rounded-md
        border-2 px-4 py-3 text-sm font-bold text-blue-700 transition-colors hover:bg-blue-100"
            >
              Become a tutor
            </a>
          </Link>
        </>
      )}
    </nav>
  );
}

interface UserDropdownProps {
  fullname: Fullname;
}

function UserDropdown({ fullname }: UserDropdownProps) {
  const router = useRouter();

  const logoutHandler = async () => {
    if ((await authService.logout()).status == 200) {
      // Successfully logged out
      router.push("/login");
    } else {
      // Failed to log out
    }
  };

  return (
    <div className="text-right">
      <Menu as="div" className="relative inline-block text-left">
        <div>
          <Menu.Button className="focus-visible:ring-blue inline-flex w-full justify-center rounded-md px-4 py-2.5 text-sm font-bold text-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-200 focus:ring-offset-2">
            {`${fullname.first_name} ${fullname.last_name}`}
            <ChevronDownIcon
              className="ml-2 -mr-1 h-5 w-5 text-gray-500 hover:text-gray-800"
              aria-hidden="true"
            />
          </Menu.Button>
        </div>
        <Transition
          as={Fragment}
          enter="transition ease-out duration-100"
          enterFrom="transform opacity-0 scale-95"
          enterTo="transform opacity-100 scale-100"
          leave="transition ease-in duration-75"
          leaveFrom="transform opacity-100 scale-100"
          leaveTo="transform opacity-0 scale-95"
        >
          <Menu.Items className="absolute right-0 mt-2 w-56 origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
            <div className="px-1 py-1 ">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-blue-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                  >
                    Information (Icon?)
                  </button>
                )}
              </Menu.Item>
            </div>
            <div className="px-1 py-1">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-rose-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                    onClick={logoutHandler}
                  >
                    Logout (Icon?)
                  </button>
                )}
              </Menu.Item>
            </div>
          </Menu.Items>
        </Transition>
      </Menu>
    </div>
  );
}

function ForParentDropdown({
  isActive,
}: {
  isActive: (curPath: string) => boolean;
}) {
  return (
    <div className="text-right">
      <Menu as="div" className="relative inline-block text-left">
        <div>
          <Menu.Button className="focus-visible:ring-blue inline-flex w-full justify-center rounded-md px-4 py-2.5 text-sm text-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-200 focus:ring-offset-2">
            <Link href="#">
              <a
                className={
                  "whitespace-nowrap text-sm hover:underline " +
                  (isActive("#") &&
                    "font-bold underline decoration-blue-700 decoration-4 underline-offset-8")
                }
              >
                For Parents
              </a>
            </Link>
            <ChevronDownIcon
              className="ml-2 -mr-1 h-5 w-5 text-gray-500 hover:text-gray-800"
              aria-hidden="true"
            />
          </Menu.Button>
        </div>
        <Transition
          as={Fragment}
          enter="transition ease-out duration-100"
          enterFrom="transform opacity-0 scale-95"
          enterTo="transform opacity-100 scale-100"
          leave="transition ease-in duration-75"
          leaveFrom="transform opacity-100 scale-100"
          leaveTo="transform opacity-0 scale-95"
        >
          <Menu.Items className="absolute right-0 mt-2 w-56 origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
            <div className="px-1 py-1 ">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-blue-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                  >
                    Information (Icon?)
                  </button>
                )}
              </Menu.Item>
            </div>
            <div className="px-1 py-1">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-blue-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                  >
                    Information (Icon?)
                  </button>
                )}
              </Menu.Item>
            </div>
          </Menu.Items>
        </Transition>
      </Menu>
    </div>
  );
}
function ForTutorDropdown({
  isActive,
}: {
  isActive: (curPath: string) => boolean;
}) {
  return (
    <div className="text-right">
      <Menu as="div" className="relative inline-block text-left">
        <div>
          <Menu.Button className="focus-visible:ring-blue inline-flex w-full justify-center rounded-md px-4 py-2.5 text-sm text-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-200 focus:ring-offset-2">
            <Link href="#">
              <a
                className={
                  "whitespace-nowrap text-sm hover:underline " +
                  (isActive("#") &&
                    "font-bold underline decoration-blue-700 decoration-4 underline-offset-8")
                }
              >
                For tutors
              </a>
            </Link>
            <ChevronDownIcon
              className="ml-2 -mr-1 h-5 w-5 text-gray-500 hover:text-gray-800"
              aria-hidden="true"
            />
          </Menu.Button>
        </div>
        <Transition
          as={Fragment}
          enter="transition ease-out duration-100"
          enterFrom="transform opacity-0 scale-95"
          enterTo="transform opacity-100 scale-100"
          leave="transition ease-in duration-75"
          leaveFrom="transform opacity-100 scale-100"
          leaveTo="transform opacity-0 scale-95"
        >
          <Menu.Items className="absolute right-0 mt-2 w-56 origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
            <div className="px-1 py-1 ">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-blue-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                  >
                    Information (Icon?)
                  </button>
                )}
              </Menu.Item>
            </div>
            <div className="px-1 py-1">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-blue-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                  >
                    Information (Icon?)
                  </button>
                )}
              </Menu.Item>
            </div>
          </Menu.Items>
        </Transition>
      </Menu>
    </div>
  );
}

import Link from "next/link";

export default function Footer() {
  return (
    <footer className="grid grid-cols-3 gap-2 bg-gray-100 py-9 px-40">
      <section className="flex flex-col gap-3">
        <h3 className="text-xl font-bold">Subject</h3>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Maths</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Literature</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">English</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Physics</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Chemistry</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Geography</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">History</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Biology</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Technology</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Information Technology
          </a>
        </Link>
      </section>
      <section className="flex flex-col gap-3">
        <h3 className="text-xl font-bold">About</h3>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Careers</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Blogs</a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Become a tutor
          </a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Privacy Policy
          </a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Terms of Service
          </a>
        </Link>
      </section>
      <section className="flex flex-col gap-3">
        <h3 className="text-xl font-bold">Support</h3>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Help & Support
          </a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Trust & Safety
          </a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">
            Payment Policy
          </a>
        </Link>
        <Link href="#">
          <a className="transition-colors hover:text-gray-400">Refund Policy</a>
        </Link>
      </section>
      <div
        id="divider"
        className="col-span-3 my-5 h-0.5 rounded-sm bg-gray-300"
      ></div>
      <p>© 2022, Home Tutor</p>
      <div className="col-span-2 ml-auto flex gap-2">
        <p>🦄</p>
        <p>🐈</p>
        <p>🐕</p>
      </div>
    </footer>
  );
}

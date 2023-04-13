using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using CuaHangBanDienThoai.Models;

namespace CuaHangBanDienThoai.Controllers
{
    public class HinhAnhsController : Controller
    {
        private CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();

        // GET: HinhAnhs
        public ActionResult Index(int MaSP)
        {
            return View(db.HinhAnhs.Where(x => x.MaSP == MaSP).ToList());
        }

        // GET: HinhAnhs/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            HinhAnh hinhAnh = db.HinhAnhs.Find(id);
            if (hinhAnh == null)
            {
                return HttpNotFound();
            }
            return View(hinhAnh);
        }

        // GET: HinhAnhs/Create
        public ActionResult Create()
        {
            ViewBag.SanPham = new SelectList(db.SanPhams, "MaSP", "TenSP");
            return View();
        }

        // POST: HinhAnhs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaHinhAnh,HinhAnh1,MaSP")] HinhAnh hinhAnh)
        {

            ViewBag.SanPham = new SelectList(db.SanPhams, "MaSP", "TenSP");
            if (ModelState.IsValid)
            {
                db.HinhAnhs.Add(hinhAnh);
                db.SaveChanges();
                return RedirectToAction("Index", new { MaSP = hinhAnh.MaSP });
            }

            return View(hinhAnh);
        }

        // GET: HinhAnhs/Edit/5
        public ActionResult Edit(int? id)
        {
            ViewBag.SanPham = new SelectList(db.SanPhams, "MaSP", "TenSP");
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            HinhAnh hinhAnh = db.HinhAnhs.Find(id);
            if (hinhAnh == null)
            {
                return HttpNotFound();
            }
            return View(hinhAnh);
        }

        // POST: HinhAnhs/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaHinhAnh,HinhAnh1,MaSP")] HinhAnh hinhAnh)
        {
            ViewBag.SanPham = new SelectList(db.SanPhams, "MaSP", "TenSP");
            if (ModelState.IsValid)
            {
                db.Entry(hinhAnh).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index", new { MaSP = hinhAnh.MaSP });
            }
            return View(hinhAnh);
        }

        // GET: HinhAnhs/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            HinhAnh hinhAnh = db.HinhAnhs.Find(id);
            if (hinhAnh == null)
            {
                return HttpNotFound();
            }
            return View(hinhAnh);
        }

        // POST: HinhAnhs/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            HinhAnh hinhAnh = db.HinhAnhs.Find(id);
            db.HinhAnhs.Remove(hinhAnh);
            db.SaveChanges();
            return RedirectToAction("Index", new { MaSP = hinhAnh.MaSP });
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}

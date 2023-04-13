using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using CuaHangBanDienThoai.BUS;
using CuaHangBanDienThoai.Models;

namespace CuaHangBanDienThoai.Controllers
{
    public class SanPhamsController : Controller
    {
        private SanPhamAPI api = new SanPhamAPI();
        private HangAPI hangAPI = new HangAPI();
       

        // GET: SanPhams
        public ActionResult Index()
        {
            return View(api.GetSanPhamsResults());
        }

        // GET: SanPhams/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            GetSanPhamID_Result sanPham = api.GetSanPhamsResult(id.Value);
            if (sanPham == null)
            {
                return HttpNotFound();
            }
            return View(sanPham);
        }

        // GET: SanPhams/Create
        public ActionResult Create()
        {
            ViewBag.Hang = new SelectList(hangAPI.GetHangs(), "MaHang", "TenHang");
         
            return View();
        }

        // POST: SanPhams/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaSP,TenSP,SoLuong,GiaBan,HinhAnh,MoTa,MaHang,MaLoai")] SanPham sanPham)
        {
            ViewBag.Hang = new SelectList(hangAPI.GetHangs(), "MaHang", "TenHang");
          
            if (ModelState.IsValid)
            {
                api.AddSanPham(sanPham);
                return RedirectToAction("Index");
            }

            return View(sanPham);
        }

        // GET: SanPhams/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ViewBag.Hang = new SelectList(hangAPI.GetHangs(), "MaHang", "TenHang");
           
            SanPham sanPham = api.GetSanPham(id.Value);
            if (sanPham == null)
            {
                return HttpNotFound();
            }
            return View(sanPham);
        }

        // POST: SanPhams/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaSP,TenSP,SoLuong,GiaBan,HinhAnh,MoTa,MaHang,MaLoai")] SanPham sanPham)
        {
            ViewBag.Hang = new SelectList(hangAPI.GetHangs(), "MaHang", "TenHang");
         
            if (ModelState.IsValid)
            {
                api.UpdateSanPham(sanPham);
                return RedirectToAction("Index");
            }
            return View(sanPham);
        }

        // GET: SanPhams/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SanPham sanPham = api.GetSanPham(id.Value);
            if (sanPham == null)
            {
                return HttpNotFound();
            }
            return View(sanPham);
        }

        // POST: SanPhams/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            api.DeleteSanPham(id);
            return RedirectToAction("Index");
        }

        
    }
}

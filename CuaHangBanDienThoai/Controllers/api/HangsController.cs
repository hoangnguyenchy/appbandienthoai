using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using CuaHangBanDienThoai.Models;

namespace CuaHangBanDienThoai.Controllers.api
{
    public class HangsController : ApiController
    {
        private CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();

        // GET: api/Hangs
        public IQueryable<Hang> GetHangs()
        {
            return db.Hangs;
        }

        // GET: api/Hangs/5
        [ResponseType(typeof(Hang))]
        public IHttpActionResult GetHang(int id)
        {
            Hang hang = db.Hangs.Find(id);
            if (hang == null)
            {
                return NotFound();
            }

            return Ok(hang);
        }

        // PUT: api/Hangs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutHang(int id, Hang hang)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != hang.MaHang)
            {
                return BadRequest();
            }

            db.Entry(hang).State = EntityState.Modified;

            return Ok(db.SaveChanges());
        }

        // POST: api/Hangs
        [ResponseType(typeof(Hang))]
        public IHttpActionResult PostHang(Hang hang)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Hangs.Add(hang);
            return Ok(db.SaveChanges());
        }

        // DELETE: api/Hangs/5
        [ResponseType(typeof(Hang))]
        public IHttpActionResult DeleteHang(int id)
        {
            Hang hang = db.Hangs.Find(id);
            if (hang == null)
            {
                return NotFound();
            }

            db.Hangs.Remove(hang);
            

            return Ok(db.SaveChanges());
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool HangExists(int id)
        {
            return db.Hangs.Count(e => e.MaHang == id) > 0;
        }
    }
}
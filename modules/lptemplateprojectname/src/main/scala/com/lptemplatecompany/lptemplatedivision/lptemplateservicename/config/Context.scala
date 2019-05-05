package com.lptemplatecompany.lptemplatedivision.lptemplateservicename
package config

import com.lptemplatecompany.lptemplatedivision.lptemplateservicename.algebra.ServiceAlg
import com.lptemplatecompany.lptemplatedivision.lptemplateservicename.interpreter.Service
import com.lptemplatecompany.lptemplatedivision.shared.log4zio.Logger
import scalaz.zio.ZManaged

/**
  * Top level application resources held in a ZManaged[AppEnv, ...] so that proper cleanup happens
  * on program termination, whether clean or failure.
  */
// TODO how does this interact with zio.service idea?
final case class Context[F[_]] private(
  service: ServiceAlg[F]
)

object Context {
  def create(log: Logger[AIO]): ZManaged[RuntimeEnv, AppError, Context[AIO]] =
    for {
      service <- Service.managed(log)
    } yield new Context[AIO](service)
}
